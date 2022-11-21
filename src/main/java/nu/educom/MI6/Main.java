package nu.educom.MI6;

import nu.educom.MI6.data.Crud;
import nu.educom.MI6.presenters.Presentor;
import nu.educom.MI6.views.JFrameView;

public class Main {

  public static void main(String[] args) {
    var crud= new Crud();
    var view = new JFrameView();
    var presenter = new Presentor(view, crud);

    presenter.displayLogin();
  }
}