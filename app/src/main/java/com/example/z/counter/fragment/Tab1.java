package com.example.z.counter.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.z.counter.R;
import com.example.z.counter.activity.MainActivity;
import com.example.z.counter.activity.SharedHelper;
import com.example.z.counter.helper.Userhelper;
import com.example.z.counter.model.User;

import java.io.ByteArrayOutputStream;

/**
 * Created by z on 28/11/17.
 */

public class Tab1 extends Fragment implements View.OnClickListener{
    User userclas;
    View v;
    ImageView imgProfil;
    RoundedBitmapDrawable roundedBitmapDrawablesinfoto;
    Bitmap bitmapsinfoto;

    EditText editProfilName , editProfilEmail , editProfilPN, editconfirmPass;
    TextView urProf;
    Button updateProfil;

    //profil image
    private int request_code = 1;
    private byte[] bytes;


    Userhelper userhelper;
    long idbundle;
    long idfix;
    String pass, user;
    Context context;
    SharedHelper sharedHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.profil_account,container, false);
         context=getActivity();
        initView();
        initObjeck();
        getData();
        logedOrNot();
        getroundedImage();
        imgProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                //verificacion de la version de plataforma
                if(Build.VERSION.SDK_INT < 19){
                    //android 4.3  y anteriores
                    i = new Intent();
                    i.setAction(Intent.ACTION_GET_CONTENT);
                }else {
                    //android 4.4 y superior
                    i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                }
                i.setType("image/*");
                startActivityForResult(i, request_code);
            }
        });

        return v;
    }

    private void logedOrNot() {
        userclas= userhelper.getDataUser(idbundle);

        if (((MainActivity)context).getSatu()){
            sharedHelper.masukanDataAwalUser(idbundle, pass);
            String a = user.substring(0,1).toUpperCase()+user.substring(1);
            urProf.setText(a+" Profil");
            editProfilName.setText(userclas.getNama());
            editProfilEmail.setText(userclas.getEmail());
            editProfilPN.setText(userclas.getPhonenumber());


            if(userclas.getBytes()!=null){
                byte[] foodImage = userclas.getBytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

                imgProfil.setImageBitmap(bitmap);


                Bitmap bitmap2 = ((BitmapDrawable)imgProfil.getDrawable()).getBitmap();

                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
                roundedBitmapDrawable.setCircular(true);

                imgProfil.setImageDrawable(roundedBitmapDrawable);

/*
                Bitmap bitmap = ((BitmapDrawable)imgProfil.getDrawable()).getBitmap();
                roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                roundedBitmapDrawablesinfoto.setCircular(true);
                imgProfil.setImageDrawable(roundedBitmapDrawablesinfoto);*/
            }
        }else {
            String a = userclas.getUsernama().substring(0,1).toUpperCase()+userclas.getUsernama().substring(1);

            urProf.setText(userclas.getUsernama()+" Profil ");
            editProfilName.setText(userclas.getNama());
            editProfilEmail.setText(userclas.getEmail());
            editProfilPN.setText(userclas.getPhonenumber());


            if(userclas.getBytes()!=null){
                byte[] foodImage = userclas.getBytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

                imgProfil.setImageBitmap(bitmap);


                Bitmap bitmap2 = ((BitmapDrawable)imgProfil.getDrawable()).getBitmap();

                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
                roundedBitmapDrawable.setCircular(true);

                imgProfil.setImageDrawable(roundedBitmapDrawable);

            }
        }







    }

    private void getData() {

            idbundle=((MainActivity)context).getId();
            Log.i("iniidbundle", ""+idbundle);
            if (idbundle>0){
                pass=((MainActivity)context).getPass();
                user=((MainActivity)context).getUser();
            }else {
                idbundle=sharedHelper.getID();
            }
        Log.i("iniidbundle", ""+idbundle);



    }

    private void initObjeck() {
        userclas=new User();
        sharedHelper=new SharedHelper(getActivity().getApplicationContext());
        userhelper= new Userhelper(getActivity().getApplicationContext());
    }


    private void initView() {
        imgProfil= v.findViewById(R.id.imgProfil);
        editProfilName = v.findViewById(R.id.editProfilName);
        editProfilEmail= v.findViewById(R.id.editProfilEmail);
        editProfilPN = v.findViewById(R.id.editProfilPN);
        updateProfil=v.findViewById(R.id.btnUpdateProfil);
        urProf=v.findViewById(R.id.txtYourprofil);
        updateProfil.setOnClickListener(this);
        editconfirmPass=v.findViewById(R.id.edtConfirmPass);

    }
    private void getroundedImage() {
         bitmapsinfoto = BitmapFactory.decodeResource(getResources(),R.drawable.images);
         roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmapsinfoto);
        roundedBitmapDrawablesinfoto.setCircular(true);
        imgProfil.setImageDrawable(roundedBitmapDrawablesinfoto);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == getActivity().RESULT_OK && requestCode == request_code){
            imgProfil.setImageURI(data.getData());
            bytes = imageToByte(imgProfil);
            // para que se vea la imagen en circulo
            Bitmap bitmap = ((BitmapDrawable)imgProfil.getDrawable()).getBitmap();
            roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmapDrawablesinfoto.setCircular(true);
            imgProfil.setImageDrawable(roundedBitmapDrawablesinfoto);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private byte[] imageToByte(ImageView image) {
        Bitmap bitmapFoto = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapFoto.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdateProfil:
                if (userhelper.cekPassword(editconfirmPass.getText().toString())){
                    userhelper.updateData(idbundle, bytes, editProfilName.getText().toString(),
                            editProfilEmail.getText().toString(), editProfilPN.getText().toString());

                }else {
                    Snackbar.make(getView(), "Password Salah", Snackbar.LENGTH_LONG).show();
                }


        }
    }



}
