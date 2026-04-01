import { Size } from './size.enum';
import { Tier } from './tier.enum';

export interface Effect {
  effectType: 'ACTIVE' | 'PASSIVE';
  cooldowns: number[];
  text: string;
}

export interface Item {
  id: string;
  name: string;
  tier: Tier;
  size: Size;
  heroes: string[];
  tags: string[];
  hiddenTags: string[];
  effects: Effect[];
}
