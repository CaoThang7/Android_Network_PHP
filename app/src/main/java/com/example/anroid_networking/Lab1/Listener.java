package com.example.anroid_networking.Lab1;

import android.graphics.Bitmap;

public interface Listener {
  void onImageLoaded(Bitmap bitmap);
  void onError();
}
