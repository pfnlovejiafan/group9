package com.example.ceshi_06.mvp.di.component;

import com.example.ceshi_06.mvp.mvp.model.OkManager;
import com.example.ceshi_06.mvp.mvp.model.OkManager_ProApiServiceFactory;
import com.example.ceshi_06.mvp.mvp.model.RxOpretorImpl;
import com.example.ceshi_06.mvp.mvp.model.RxOpretorImpl_MembersInjector;
import com.example.ceshi_06.mvp.mvp.model.api.ApiService;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerOkComponent implements OkComponent {
  private Provider<ApiService> proApiServiceProvider;

  private MembersInjector<RxOpretorImpl> rxOpretorImplMembersInjector;

  private DaggerOkComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static OkComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.proApiServiceProvider =
        DoubleCheck.provider(OkManager_ProApiServiceFactory.create(builder.okManager));

    this.rxOpretorImplMembersInjector = RxOpretorImpl_MembersInjector.create(proApiServiceProvider);
  }

  @Override
  public void getSingleApiService(RxOpretorImpl impl) {
    rxOpretorImplMembersInjector.injectMembers(impl);
  }

  public static final class Builder {
    private OkManager okManager;

    private Builder() {}

    public OkComponent build() {
      if (okManager == null) {
        this.okManager = new OkManager();
      }
      return new DaggerOkComponent(this);
    }

    public Builder okManager(OkManager okManager) {
      this.okManager = Preconditions.checkNotNull(okManager);
      return this;
    }
  }
}
