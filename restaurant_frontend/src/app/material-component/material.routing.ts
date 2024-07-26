import { Routes } from '@angular/router';
import { ManageCategoryComponent } from './manage-category/manage-category.component';
import { ManageProductComponent } from './manage-product/manage-product.component';
import { ManageOrderComponent } from './manage-order/manage-order.component';
import { ViewBillComponent } from './view-bill/view-bill.component';
import { ManageUserComponent } from './manage-user/manage-user.component';
import { RouteGuardService } from '../services/route-guard.service';

export const MaterialRoutes: Routes = [
  {
    path: 'categories',
    component: ManageCategoryComponent,
    canActivate: [RouteGuardService],
    data: { expectedRole: ['ADMIN',  'USER'] },
  },
  {
    path: 'products',
    component: ManageProductComponent,
    canActivate: [RouteGuardService],
    data: { expectedRole: ['ADMIN',  'USER'] },
  },
  {
    path: 'orders',
    component: ManageOrderComponent,
    canActivate: [RouteGuardService],
    data: { expectedRole: ['ADMIN', 'USER'] },
  },
  {
    path: 'bills',
    component: ViewBillComponent,
    canActivate: [RouteGuardService],
    data: { expectedRole: ['ADMIN', 'USER'] },
  },
  {
    path: 'users',
    component: ManageUserComponent,
    canActivate: [RouteGuardService],
    data: { expectedRole: ['ADMIN'] },
  },
];
