package com.nvjweb.litletourettev1.data;

import android.provider.BaseColumns;

public class UsuarioContract {
    public static abstract class UsuariosEntry implements BaseColumns {
        public static final String TABLE_NAME ="usuario";

        public static final String _ID = "_ID";
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String FECHA_NACIMIENTO = "FECHA_NACIMIENTO";
        public static final String AVATAR = "AVATAR";
        public static final String GUARDADO = "GUARDADO";


    }

}
