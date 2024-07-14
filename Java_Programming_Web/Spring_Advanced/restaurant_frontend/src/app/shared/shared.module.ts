import { NgModule } from '@angular/core';

import {
  AccordionAnchorDirective,
  AccordionLinkDirective,
  AccordionDirective,
} from './accordion';
import { MenuItems } from './menu-items';
import { TranslationSwitcherComponent } from '../material-component/translation/translation-switcher/translation-switcher.component';

@NgModule({
  declarations: [
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,

  ],
  exports: [
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,
  ],
  providers: [MenuItems],
})
export class SharedModule {}
