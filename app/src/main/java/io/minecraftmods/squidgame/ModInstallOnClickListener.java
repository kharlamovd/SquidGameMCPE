package io.minecraftmods.squidgame;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.RawRes;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ModInstallOnClickListener implements View.OnClickListener {

    private final int rawResId;
    private final String extension;

    public ModInstallOnClickListener(int rawResId, String ext) {
        this.rawResId = rawResId;
        this.extension = ext;
    }

    @Override
    public void onClick(View view) {

        Context context = view.getContext();

        File modFile = getRawFile(context, extension, rawResId);
        openModFile(modFile, context);

    }

    private File getRawFile(Context context, String ext, @RawRes int rawId) {
        File file = new File(context.getCacheDir() + File.separator + "mod." + ext);
        try {
            InputStream inputStream = context.getResources().openRawResource(rawId);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] buf = new byte[1024];
            int len;

            while ((len = inputStream.read(buf)) > 0) {
                fileOutputStream.write(buf, 0, len);
            }

            fileOutputStream.close();
            inputStream.close();

            return file;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    private void openModFile(File modFile, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        /*intent.setType("file/*").
                setData(Uri.parse("minecraft://?import=" + modFile.getPath()));*/
        String myProvider = context.getApplicationContext().getPackageName() + ".provider";
        intent.setData(FileProvider.getUriForFile(context, myProvider, modFile));
        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_GRANT_READ_URI_PERMISSION |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        context.startActivity(intent);
    }

}
