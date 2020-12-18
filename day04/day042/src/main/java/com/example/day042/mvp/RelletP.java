package com.example.day042.mvp;

<<<<<<< HEAD
import android.util.Log;

=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
public class RelletP {
    private final RelletM relletM;
    private IView iView;

    public RelletP(IView iView) {
        this.iView = iView;
        relletM = new RelletM();
    }

    public void start() {
        relletM.Json(new Cellet() {
            @Override
            public void showOk(Object object) {
                iView.showOk(object);
            }

            @Override
            public void showNo(String string) {
                iView.showNo(string);
            }
        });
<<<<<<< HEAD

    }

    public void JsonPao() {
=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
        relletM.JsonPao(new Cellet() {
            @Override
            public void showOk(Object object) {
                iView.showOk(object);
            }

            @Override
            public void showNo(String string) {
                iView.showNo(string);
            }
        });
    }
<<<<<<< HEAD

    public void startShe() {
        relletM.JsonShe(new Cellet() {
            @Override
            public void showOk(Object object) {
                Log.e("123",object+"");
                iView.showOk(object);
            }

            @Override
            public void showNo(String string) {
                iView.showNo(string);
            }
        });
    }
=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
}
