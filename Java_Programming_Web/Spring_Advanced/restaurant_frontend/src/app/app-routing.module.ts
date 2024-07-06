// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { RouterModule, Routes } from '@angular/router';
// import { HomeComponent } from './home/home.component';
// import { FullComponent } from './layouts/full/full.component';
// import { RouteGuardService } from './services/route-guard.service';

// const routes: Routes = [
//   { path: '', component: HomeComponent, },
//   {
//     path: 'restaurant',
//     component: FullComponent,
//     children: [
//       {
//         path: '',
//         redirectTo: '/restaurant/dashboard',
//         pathMatch: 'full',
//       },
//       {
//         path: '',
//         loadChildren: () =>
//           import('./material-component/material.module').then(
//             (m) => m.MaterialComponentsModule
//           ),
//         canActivate: [RouteGuardService],
//         data: {
//           expectedRole: ['admin', 'user'],
//         },
//       },
//       {
//         path: 'dashboard',
//         loadChildren: () =>
//           import('./dashboard/dashboard.module').then((m) => m.DashboardModule),
//         canActivate: [RouteGuardService],
//         data: {
//           expectedRole: ['admin', 'user'],
//         },
//       },
//     ],
//   },
//   { path: '**', component: HomeComponent },
// ];

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule],
// })
// export class AppRoutingModule {}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FullComponent } from './layouts/full/full.component';
import { RouteGuardService } from './services/route-guard.service';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'restaurant',
    component: FullComponent,
    children: [
      {
        path: '',
        redirectTo: '/restaurant/dashboard',
        pathMatch: 'full',
      },
      {
        path: '',
        loadChildren:
          () => import('./material-component/material.module').then(m => m.MaterialComponentsModule),
      },
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
      }
    ]
  },
  { path: '**', component: HomeComponent }
];


// const routes: Routes = [
//   { path: '', component: HomeComponent, canActivate: [RouteGuardService] },
//   {
//     path: 'restaurant',
//     component: FullComponent,
//     canActivate: [RouteGuardService],
//     data: { expectedRole: ['admin', 'user'] },
//     children: [
//       {
//         path: '',
//         redirectTo: '/restaurant/dashboard',
//         pathMatch: 'full',
//       },
//       {
//         path: 'dashboard',
//         loadChildren: () =>
//           import('./dashboard/dashboard.module').then((m) => m.DashboardModule),
//         canActivate: [RouteGuardService],
//         data: { expectedRole: ['admin', 'user'] },
//       },
//       {
//         path: '',
//         loadChildren:
//           () => import('./material-component/material.module').then(m => m.MaterialComponentsModule),
//           canActivate: [RouteGuardService],
//       },
//       {
//         path: 'categories',
//         loadChildren: () =>
//           import('./material-component/material.module').then(
//             (m) => m.MaterialComponentsModule
//           ),
//         canActivate: [RouteGuardService],
//         data: { expectedRole: ['admin'] },
//       },
//       {
//         path: 'products',
//         loadChildren: () =>
//           import('./material-component/material.module').then(
//             (m) => m.MaterialComponentsModule
//           ),
//         canActivate: [RouteGuardService],
//         data: { expectedRole: ['admin'] },
//       },
//       {
//         path: 'orders',
//         loadChildren: () =>
//           import('./material-component/material.module').then(
//             (m) => m.MaterialComponentsModule
//           ),
//         canActivate: [RouteGuardService],
//         data: { expectedRole: ['admin', 'user'] },
//       },
//       {
//         path: 'bills',
//         loadChildren: () =>
//           import('./material-component/material.module').then(
//             (m) => m.MaterialComponentsModule
//           ),
//         canActivate: [RouteGuardService],
//         data: { expectedRole: ['admin', 'user'] },
//       },
//       {
//         path: 'users',
//         loadChildren: () =>
//           import('./material-component/material.module').then(
//             (m) => m.MaterialComponentsModule
//           ),
//         canActivate: [RouteGuardService],
//         data: { expectedRole: ['admin'] },
//       },
//     ],
//   },
//   { path: '**', redirectTo: '/' },
// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
