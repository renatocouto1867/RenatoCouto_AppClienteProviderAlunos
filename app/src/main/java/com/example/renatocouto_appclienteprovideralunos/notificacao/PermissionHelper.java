package com.example.renatocouto_appclienteprovideralunos.notificacao;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {
    private static final int REQUEST_ID= 3;
    private AppCompatActivity activity;
    private NotificationHelper notificacationHelper;

    public PermissionHelper(AppCompatActivity activity, NotificationHelper notificacationHelper) {
        this.activity = activity;
        this.notificacationHelper = notificacationHelper;
    }//construtor

    public boolean temPermissao(){
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.POST_NOTIFICATIONS)== PackageManager.PERMISSION_GRANTED;
    }

    public void solicitaPermissao(){
        ActivityCompat.requestPermissions(activity,new String[]
                {Manifest.permission.POST_NOTIFICATIONS},REQUEST_ID);
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResult){
        if (requestCode == REQUEST_ID){
            if (permissions.length>0 && permissions[0]
                    .equals(Manifest.permission.POST_NOTIFICATIONS)){
                notificacationHelper.gerarNotificacao("solicita permissao");
            }else {
                Toast.makeText(activity, "Permisão negada", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void verificaPermissao(){
        if(!temPermissao()){
            solicitaPermissao();
        }
    }

}
