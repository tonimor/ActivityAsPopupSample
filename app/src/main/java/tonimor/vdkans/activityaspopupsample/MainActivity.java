package tonimor.vdkans.activityaspopupsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButtonUp = (Button)findViewById(R.id.my_button_up);
        myButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPopupActivity();
            }
        });

        Button myButtonDown = (Button)findViewById(R.id.my_button_down);
        myButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPopdownActivity();
            }
        });
    }

    protected void startPopupActivity()
    {
        final int request_code = 1234;
        Intent intent = new Intent(this, PopupActivity.class);
        startActivityForResult(intent, request_code);
    }

    protected void startPopdownActivity()
    {
        final int request_code = 1234;
        Intent intent = new Intent(this, PopdownActivity.class);
        startActivityForResult(intent, request_code);
    }
}
