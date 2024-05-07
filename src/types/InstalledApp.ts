import {Base64} from './Base64';

export type InstalledApp = {
  packageName: string;
  appName: string;
  appIconBase64: Base64;
  locked: boolean;
};
