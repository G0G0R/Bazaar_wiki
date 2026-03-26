import {Size} from './size.enum';

export interface Item {
  id: string;
  name: string;
  tier: string;
  heroes: string[];
  tags: string[];
  hiddenTags: string[];
  size: Size;
}
