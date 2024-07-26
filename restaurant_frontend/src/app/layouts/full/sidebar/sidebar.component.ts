import { MediaMatcher } from '@angular/cdk/layout';
import { ChangeDetectorRef, Component, OnDestroy } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { MenuItems, Menu } from 'src/app/shared/menu-items';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: [],
})
export class AppSidebarComponent implements OnDestroy {
  mobileQuery: MediaQueryList;
  userRoles: string[];
  private _mobileQueryListener: () => void;

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    public menuItems: MenuItems
  ) {
    const token = localStorage.getItem('token');
    if (token) {
      const tokenPayload: any = jwtDecode(token);
      const userRoles = tokenPayload.roles.map((role: string) => role.replace('ROLE_', ''));
      this.userRoles = tokenPayload.roles.map((role: string) =>
        role.replace('ROLE_', '')
      );
    } else {
      this.userRoles = [];
    }

    this.mobileQuery = media.matchMedia('(min-width: 768px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  hasRole(requiredRole: string): boolean {
    return requiredRole === '' || this.userRoles.includes(requiredRole);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }
}
