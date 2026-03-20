import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Item } from '../../features/items/models/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private readonly apiUrl = 'http://localhost:8080/api/items';

  constructor(private readonly http: HttpClient) {}

  getItems(): Observable<Item[]> {
    return this.http.get<Item[]>(this.apiUrl);
  }
}
