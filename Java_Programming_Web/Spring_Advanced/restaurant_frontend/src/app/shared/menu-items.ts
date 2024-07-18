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
    name: 'Categories',
    type: 'link',
    icon: 'category',
    role: 'ADMIN',
  },
  {
    state: 'products',
    name: 'Products',
    type: 'link',
    icon: 'inventory_2',
    role: '',
  },
  {
    state: 'orders',
    name: 'Orders',
    type: 'link',
    icon: 'shopping_cart',
    role: '',
  },
  {
    state: 'bills',
    name: 'Bills',
    type: 'link',
    icon: 'backup_table',
    role: '',
  },
  {
    state: 'users',
    name: 'Employees',
    type: 'link',
    icon: 'people',
    role: 'ADMIN',
  },
];

@Injectable()
export class MenuItems {
  getMenuItem(): Menu[] {
    return MENUITEMS;
  }
}
