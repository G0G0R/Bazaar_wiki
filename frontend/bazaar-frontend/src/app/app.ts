import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ItemService } from './services/item.service';
import { Item } from './models/item.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App implements OnInit {

  // 🔹 Liste complète venant de l'API
  items: Item[] = [];

  // 🔹 Texte de la barre de recherche
  searchTerm: string = '';

  constructor(private itemService: ItemService) {}

  ngOnInit(): void {
    this.itemService.getItems().subscribe({
      next: (data: Item[]) => {
        this.items = data;
      },
      error: (err: any) => {
        console.error('Erreur API :', err);
      }
    });
  }

  // 🔹 Méthode appelée dans le template
  filteredItems(): Item[] {
    // Si rien n'est tapé → on retourne tout
    if (!this.searchTerm) {
      return this.items;
    }

    // Sinon → on filtre
    return this.items.filter(item =>
      item.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
