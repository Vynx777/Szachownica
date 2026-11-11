package com.example.szachownica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import static java.lang.Math.min;


public class MainActivity extends AppCompatActivity {
    Panel panel;
    Paint kolor_tla1 = new Paint();
    Paint kolor_tla2 = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(panel = new Panel(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.autor) {
            Context context = getApplicationContext();
            CharSequence text = "Autor: Bartosz Fiksa";
            int czas = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, czas);
            toast.setGravity(Gravity.BOTTOM | Gravity.BOTTOM, 0, 0);
            toast.show();
            return true;
        } else if (id == R.id.powrot) {
            finish();
            return true;
        } else if (id == R.id.color01) {
            kolor_tla1.setColor(Color.WHITE);
            kolor_tla2.setColor(Color.BLACK);
            panel.postInvalidate();
            return true;
        } else if (id == R.id.color02) {
            kolor_tla1.setColor(Color.YELLOW);
            kolor_tla2.setColor(Color.RED);
            panel.postInvalidate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class Panel extends View {
        public Panel(Context context) {
            super(context);
            kolor_tla1.setColor(Color.WHITE);
            kolor_tla2.setColor(Color.BLACK);
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GRAY);

            float width = getWidth();
            float height = getHeight();
            float bokSzachownicy = min(width, height);
            float wielkoscPola = bokSzachownicy / 8;

            for(int i=0; i<8; i++) {
                for(int j=0; j<8; j++) {
                    float left = i * wielkoscPola;
                    float top = j * wielkoscPola;
                    float right = (i + 1) * wielkoscPola;
                    float bottom = (j + 1) * wielkoscPola;

                    if((i+j)%2==0) {
                        canvas.drawRect(left, top, right, bottom, kolor_tla1);
                    } else {
                        canvas.drawRect(left, top, right, bottom, kolor_tla2);
                    }
                }
            }
        }
    }
}