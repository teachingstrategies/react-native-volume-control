export = RNVolumeControl;
export as namespace RNVolumeControl;

declare namespace RNVolumeControl {
  export function change(value: number): void;
  export function getVolume(): number;
}
