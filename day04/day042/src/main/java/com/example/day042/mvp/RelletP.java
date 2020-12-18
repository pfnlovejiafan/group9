package com.example.day042.mvp;

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
}
