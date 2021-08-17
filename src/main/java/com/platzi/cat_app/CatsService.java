package com.platzi.cat_app;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CatsService {

    public static void seeCats(){

        // 1. Vamos a traer los datos de la API
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String elJson = response.body().string();

            //cortar corchetes de la respuesta
            elJson = elJson.substring(1, elJson.length());
            elJson = elJson.substring(0, elJson.length()-1);

            // Crear obj clase gson
            Gson gson = new Gson();
            Cats cats = gson.fromJson(elJson, Cats.class);

            //redimensionar imagen en caso de requerirse
            Image image = null;
            try {
                URL url = new URL(cats.getUrl());
                image = ImageIO.read(url);

                ImageIcon fondoGato = new ImageIcon(image);

                if (fondoGato.getIconWidth() > 800){
                    //redimensionar
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }

                String menu = "Opciones: \n"
                        + "1. Ver otra imagen \n"
                        + "2. Favorito \n"
                        + "3. Volver \n";

                String[] botones = { " Ver otra iamgen", "favorito", "volver"};
                String id_gato  = cats.getId();
                String option = (String) JOptionPane.showInputDialog(null,
                        menu,
                        id_gato,
                        JOptionPane.INFORMATION_MESSAGE,
                        fondoGato,
                        botones,
                        botones[0]);

                int selection = -1;

                // Validamos que opcion selecciona el usuario
                for (int i = 0; i < botones.length; i++) {
                    if(option.equals(botones[i])){
                        selection = i;
                    }
                }

                switch (selection){

                    case 0:
                        seeCats();
                        break;
                    case 1:
                        favCat(cats);
                        break;
                    default:
                        break;
                }

            }catch (IOException e){
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void favCat(Cats cats){

    }
}
