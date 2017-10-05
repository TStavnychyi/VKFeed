package com.tstv.vknewsfeed.ui.dialog;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import droidninja.filepicker.FilePickerBuilder;

/**
 * Created by tstv on 27.09.2017.
 */

public class AddAttachmentDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        123);
            }
        }

        //в зависимости от выбранного типа прикрепления вызывается активити из библиотеки,
        // отображающее список элементов для прикрепления
        // после выбора передает данные через контекст в метод onActivityResult CreatePostActivity
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Прикрепить")
                .setItems(new String[]{"Фото", "Документ"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Intent intent = new Intent();
                        switch (i) {
                            case 0:
                                FilePickerBuilder
                                        .getInstance().setMaxCount(10)
                                        .showFolderView(false)
                                        .pickPhoto(getActivity());
                                break;
                            case 1:

                                FilePickerBuilder.getInstance().setMaxCount(10)
                                        .pickFile(getActivity());
                                break;
                        }

                    }
                });
        return builder.create();
    }
}