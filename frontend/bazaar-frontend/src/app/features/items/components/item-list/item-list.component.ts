import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ItemService } from '../../../../core/services/item.service';
import { Item } from '../../models/item.model';
import {ItemCardComponent} from '../item-card/item-card.component';

@Component({
  selector: 'app-item-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ItemCardComponent],
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: Item[] = [];
  displayedItems: Item[] = []; // ✅ nouvelle liste pour affichage
  searchTerm: string = '';

  constructor(private readonly itemService: ItemService) {}

  ngOnInit(): void {
    this.itemService.getItems().subscribe({
      next: (data: Item[]) => {
        this.items = data;
        this.displayedItems = data; // ✅ affichage direct
      },
      error: (err: any) => console.error(err)
    });
  }

  updateFilter(): void {
    if (!this.searchTerm) {
      this.displayedItems = this.items;
      return;
    }

    this.displayedItems = this.items.filter(item =>
      item.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
