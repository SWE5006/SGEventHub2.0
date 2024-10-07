import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";
import { commonHeader } from "../utils";

export const userReducerName = "userApi";

export const userApi = createApi({
  reducerPath: userReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL, prepareHeaders: commonHeader }),
  refetchOnMountOrArgChange: true,
  endpoints: (builder) => ({
    getUserList: builder.query({
      query: () => ({
        url: "api/user-manager/user/all",
        method: "GET",
      }),
    }),
    getUserDetails: builder.query({
      query: (userId) => ({
        url: `api/user-manager/user/search/${userId}`,
        method: "GET",
      }),
    }),
    updateUser: builder.mutation({
      query: (payload) => ({
        url: "api/user-manager/user/update",
        method: "POST",
        body: payload,
      }),
    }),
    addUser: builder.mutation({
      query: (payload) => ({
        url: "api/user-manager/user/signup",
        method: "POST",
        body: payload,
      }),
    }),
    deleteUser: builder.mutation({
      query: (userId) => ({
        url: `api/user-manager/user/delete/${userId}`,
        method: "DELETE",
      }),
    }),
  }),
});

export const selectUser = (state) => state?.[userReducerName];

export const {
  useGetUserListQuery,
  useGetUserDetailsQuery,
  useUpdateUserMutation,
  useAddUserMutation,
  useDeleteUserMutation,
} = userApi;
