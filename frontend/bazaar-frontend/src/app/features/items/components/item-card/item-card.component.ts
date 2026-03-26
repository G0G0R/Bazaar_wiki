import { Component, Input } from '@angular/core';
import { NgOptimizedImage } from '@angular/common';
import {Size} from '../../models/size.enum';

@Component({
  selector: 'app-item-card',
  standalone: true,
  imports: [NgOptimizedImage],
  templateUrl: './item-card.component.html'
})
export class ItemCardComponent {
  @Input() item: any;

  getWidth(): number {
    switch (this.item.size) {
      case Size.SMALL: return 64;
      case Size.MEDIUM: return 128;
      case Size.LARGE: return 192;
      default: return 128; // Medium
    }
  }

  getImageSrc(): string {
    switch (this.item.size) {
      case Size.SMALL: return 'assets/images/itemsicon/default-small.png';
      case Size.MEDIUM: return 'assets/images/itemsicon/default-medium.png';
      case Size.LARGE: return 'assets/images/itemsicon/default-large.png';
      default: return 'assets/images/itemsicon/default-medium.png';
    }
  }
}
