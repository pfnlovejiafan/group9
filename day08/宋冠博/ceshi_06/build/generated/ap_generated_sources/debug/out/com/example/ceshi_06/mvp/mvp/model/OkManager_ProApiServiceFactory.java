package com.example.ceshi_06.mvp.mvp.model;

import com.example.ceshi_06.mvp.mvp.model.api.ApiService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class OkManager_ProApiServiceFactory implements Factory<ApiService> {
  private final OkManager module;

  public OkManager_ProApiServiceFactory(OkManager module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public ApiService get() {
    return Preconditions.checkNotNull(
        module.proApiService(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ApiService> create(OkManager module) {
    return new OkManager_ProApiServiceFactory(module);
  }
}
