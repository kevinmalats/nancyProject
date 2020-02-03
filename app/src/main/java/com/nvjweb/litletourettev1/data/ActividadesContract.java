package com.nvjweb.litletourettev1.data;

import android.provider.BaseColumns;

public class ActividadesContract {

    public static abstract class ActividadesEntry implements BaseColumns {
        public static final String TABLE_NAME ="actividades";

        public static final String _ID = "_ID";
        public static final String IDUSUARIO = "IDUSUARIO";
        public static final String ACIERTOS = "ACIERTOS";
        public static final String ERRORES = "ERRORES";



    }
}
