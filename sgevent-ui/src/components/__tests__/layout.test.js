import { render } from "@testing-library/react";
import React from "react";
import Layout from "../Layout";
import { Provider } from "react-redux";
import configureStore from "redux-mock-store";

describe("Layout", () => {
  const initialState = {
    auth: {
      userInfo: {
        userName: "Tom",
      },
    },
  };
  const mockStore = configureStore();
  let store;
  it("should render layout", () => {
    store = mockStore(initialState);
    const { container } = render(
      <Provider store={store}>
        <Layout isLoading={false}>child comp</Layout>
      </Provider>
    );
    expect(container).toMatchSnapshot();
  });

  it("should render layout with loading status", () => {
    store = mockStore(initialState);
    const { container } = render(
      <Provider store={store}>
        <Layout isLoading={true}>child comp</Layout>
      </Provider>
    );
    expect(container).toMatchSnapshot();
  });
});
