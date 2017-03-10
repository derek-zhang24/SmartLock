package com.example.android.smartlock;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * Created by derek on 3/10/2017.
 */
public class QRActivity extends AppCompatActivity{

    /*
    created by Alexander Farber, ZXing library
     */

    public QRActivity(){

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        ImageView img = (ImageView) findViewById(R.id.QRcode);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.bad);
        img.setImageBitmap(icon);

    }

    public Bitmap generateQR(String s) throws WriterException {
        BitMatrix result;
        int w = 400;
        int h = 400;
        int WHITE = 0xFFFFFFFF;
        int BLACK = 0xFF000000;

        try{
            result = new MultiFormatWriter().encode(s, BarcodeFormat.QR_CODE, w, h, null);
        }
        catch(IllegalArgumentException e){
            return null;
        }
        int ww = result.getWidth();
        int hh = result.getHeight();
        int[] pixels = new int[ww*hh];
        for(int y=0;y<hh;y++){
            int offset = y * w;
            for(int x=0;x<ww;x++){
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        b.setPixels(pixels, 0, ww, 0, 0, ww, hh);
        return b;
    }

}
