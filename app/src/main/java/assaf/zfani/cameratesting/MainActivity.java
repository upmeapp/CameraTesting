package assaf.zfani.cameratesting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    File saveFile;
    Uri saveFileUri;
    private static int Take_PIC = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                saveFile = new File(Environment.getExternalStorageDirectory(),"test.jpg");
                saveFileUri = Uri.fromFile(saveFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,saveFileUri);
                startActivityForResult(intent,Take_PIC);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Take_PIC)
        {
            if(data!=null)
            {
                if (data.hasExtra("data"))
                {
                    Bitmap thumbnail = data.getParcelableExtra("data");
                    imageView.setImageBitmap(thumbnail);

                }
            }
            else
            {
                imageView.setImageDrawable(Drawable.createFromPath(saveFile.getPath()));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
