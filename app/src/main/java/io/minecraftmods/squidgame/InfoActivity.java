package io.minecraftmods.squidgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        AppCompatButton disclaimerButton = findViewById(R.id.disclaimerButton);
        disclaimerButton.setOnClickListener(view1 -> showDisclaimerDialog());
        AppCompatButton privPolButton = findViewById(R.id.privPolButton);
        privPolButton.setOnClickListener(
                view1 -> startActivity(new Intent(this, PrivacyPolicyActivity.class))
        );

        TextView appDescrView = findViewById(R.id.appDescrView);
        appDescrView.setMovementMethod(LinkMovementMethod.getInstance());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void showDisclaimerDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.disclaimer_text)
                .setTitle(R.string.disclaimer)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    dialog.cancel();
                });

        Dialog dialog = builder.create();
        dialog.show();
        ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());

    }

}