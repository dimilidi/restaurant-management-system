import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-translation-switcher',
  templateUrl: './translation-switcher.component.html',
  styleUrls: ['./translation-switcher.component.css']
})
export class TranslationSwitcherComponent {

  selectedLanguage = 'us'; 

  constructor(private translate: TranslateService) {
    this.translate.setDefaultLang(this.selectedLanguage);
    this.translate.use(this.selectedLanguage);
  }

  changeLanguage(lang: string) {
    this.translate.use(lang);
    this.selectedLanguage = lang;
  }

  getFlagClass(lang: string): string {
    return `fi fi-${lang}`; // Flag class for the language
  }

  getLanguageName(lang: string): string {
    switch(lang) {
      case 'us': return 'English';
      case 'fr': return 'French';
      case 'de': return 'German';
      default: return 'English';
    }
  }
}