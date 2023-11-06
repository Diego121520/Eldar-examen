import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { Guest } from 'src/app/dto/guest';
import { RestService } from 'src/app/service/rest.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  public confirmedGuestList: Guest[] = [];
  public unconfirmedGuestList: Guest[];
  public newGuest: Guest = new Guest;
  public editStates: { [id: number]: Boolean } = {};

  constructor(private restService: RestService) {

  }

  ngOnInit(): void {
    this.getAllUnconfirmedGuest();
  }

  canSendData(guest: Guest): Boolean {
    if (!guest) return false;

    for (const key in guest) {
      if (guest[key] == null || guest[key] === "") {
        return false;
      }
    }

    return true;
  }

  drop($event: CdkDragDrop<Guest[]>) {

    if ($event.previousContainer === $event.container) {
      moveItemInArray($event.container.data, $event.previousIndex, $event.currentIndex)
    } else {
      transferArrayItem(
        $event.previousContainer.data,
        $event.container.data,
        $event.previousIndex,
        $event.currentIndex)
    }
  }

  public toggleEdit(id: number) {
    this.editStates[id] = !this.editStates[id];
  }

  public addGuest() {
    if (!this.canSendData(this.newGuest)) {
      alert("Ningún campo puede quedar vacío");
      return;
    }

    this.restService.add("create", this.newGuest)
      .pipe(
        catchError((error) => {
          console.log(error);
          alert("Hubo un error al añadir al cliente");
          return of(null);
        })
      )
      .subscribe(response => {
        if (response) {
          alert("Invitado agregado exitosamente!");
        }

        this.getAllUnconfirmedGuest();
      })
  }

  public getAllConfirmedGuest() {
    this.restService.get("confirmed")
      .pipe(
        catchError(error => {
          console.log(error);
          alert("Hubo un error al traer los invitados");
          return of(null);
        })
      )
      .subscribe(response => {
        if (response) {
          this.confirmedGuestList = response;
        }
      })
  }

  public getAllUnconfirmedGuest() {
    this.restService.get("unconfirmed")
      .pipe(
        catchError(error => {
          console.log(error);
          alert("Hubo un error al traer los posibles invitados");
          return of(null);
        })
      )
      .subscribe(response => {
        if (response) {
          this.unconfirmedGuestList = response
        }
      })
  }

  public editGuest(id: number, guest: Guest) {
    if (!this.canSendData(guest)) {
      alert("Ningún campo puede quedar vacío");
      return;
    }

    this.toggleEdit(id);
    this.restService.put("update", [guest])
      .pipe(
        catchError(error => {
          console.log(error);
          alert("Hubo un error al editar el invitado");
          return of(null);
        })
      )
      .subscribe(response => {
        if (response) {
          alert("Invitado editado correctamente");
        }
      })
  }

  public confirmAssist() {
    if (this.confirmedGuestList.length === 0) {
      alert("La lista no puede estar vacía");
      return;
    }

    this.confirmedGuestList.forEach((guest) => {
      guest.confirmed = true;
    });

    this.restService.put("update", this.confirmedGuestList)
      .pipe(
        catchError(error => {
          console.log(error);
          alert("Hubo un error al enviar las invitaciones");
          return of(null);
        })
      )
      .subscribe(response => {
        if (response) {
          alert("Invitaciones enviadas!")
        }
      })

    this.confirmedGuestList = [];
  }

  public deleteGuest(id: number) {
    const confirmDelete = confirm("Seguro que desea borrar el invitado?");

    if (confirmDelete) {
      this.restService.delete("delete", id)
        .pipe(
          catchError(error => {
            console.log(error);
            alert("Hubo un error al eliminar el invitado");
            return of(null);
          })
        )
        .subscribe(response => {
          if (response) {
            alert("Invitado eliminado con exito!");
            this.getAllUnconfirmedGuest();
          }
        }
        )
    }
  }
}
