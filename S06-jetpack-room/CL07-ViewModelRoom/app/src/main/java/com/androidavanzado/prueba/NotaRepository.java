package com.androidavanzado.prueba;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.androidavanzado.prueba.db.NotaRoomDatabase;
import com.androidavanzado.prueba.db.dao.NotaDao;
import com.androidavanzado.prueba.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {
    private NotaDao notaDao;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    public NotaRepository(Application application) {
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getAllFavoritas();
    }

    public LiveData<List<NotaEntity>> getAll() { return allNotas; }

    public LiveData<List<NotaEntity>> getAllFavs() { return allNotasFavoritas; }

    public void insert (NotaEntity nota) {
        new insertAsyncTask(notaDao).execute(nota);
    }

    private static class insertAsyncTask extends AsyncTask<NotaEntity, Void, Void> {
        private NotaDao notaDatoAsyncTask;

        insertAsyncTask(NotaDao dao) {
            notaDatoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDatoAsyncTask.insert(notaEntities[0]);
            return null;
        }
    }
}
