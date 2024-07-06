import { Injectable } from '@angular/core';

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  role: string;
}

const MENUITEMS = [
  {
    state: 'dashboard',
    name: 'Dashboard',
    type: 'link',
    icon: 'dashboard',
    role: '',
  },
  {
    state: 'categories',
    name: 'Manage Categories',
    type: 'link',
    icon: 'category',
    role: 'admin',
  },
  {
    state: 'products',
    name: 'Manage Products',
    type: 'link',
    icon: 'inventory_2',
    role: 'admin',
  },
  {
    state: 'orders',
    name: 'Manage Orders',
    type: 'link',
    icon: 'shopping_cart',
    role: '',
  },
  {
    state: 'bills',
    name: 'View Bills',
    type: 'link',
    icon: 'backup_table',
    role: '',
  },
  {
    state: 'users',
    name: 'Manage Users',
    type: 'link',
    icon: 'people',
    role: 'admin',
  },
];

@Injectable()
export class MenuItems {
  getMenuItem(): Menu[] {
    return MENUITEMS;
  }
}
