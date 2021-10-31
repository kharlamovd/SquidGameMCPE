package io.minecraftmods.squidgame.extras;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import io.minecraftmods.squidgame.R;

public class DialogContainer {

    public static void showErrorDialog(Context context, String text) {

        new AlertDialog.Builder(context)
                .setTitle(R.string.uh_oh)
                .setMessage(text)

                .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public static void showDialog(Context context, String title, String text) {

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(text)

                .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

}
