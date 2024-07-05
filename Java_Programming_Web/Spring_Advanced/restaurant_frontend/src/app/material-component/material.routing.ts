import { Routes } from '@angular/router';
import { ManageCategoryComponent } from './manage-category/manage-category.component';
import { ManageProductComponent } from './manage-product/manage-product.component';
import { ManageOrderComponent } from './manage-order/manage-order.component';


export const MaterialRoutes: Routes = [
  {
    path: 'categories',
    component: ManageCategoryComponent,
  },
  {
    path: 'products',
    component: ManageProductComponent,
  },
  {
    path: 'orders',
    component: ManageOrderComponent,
  },
];
