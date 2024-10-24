import React, { useEffect } from "react";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import EditUserForm from "../../components/EditUserForm";
import {
  useGetUserDetailsQuery,
  useUpdateUserMutation,
} from "../../services/user.service";
import { useGetRoleListQuery } from "../../services/role.service";
import { navigate } from "gatsby";
import PropTypes from "prop-types";

export default function EditUser({ location }) {
  const params = new URLSearchParams(location.search);
  const userId = params.get("userId");

  const { data, isLoading } = useGetUserDetailsQuery(userId);
  const { data: roleList, isLoading: isRoleLoading } = useGetRoleListQuery();
  const [updateUser, result] = useUpdateUserMutation();

  useEffect(() => {
    if (result.isSuccess) navigate("/users");
  }, [result.isSuccess]);

  const onUpdateUser = (user) => {
    updateUser(user);
  };
  return (
    <Layout isLoading={isLoading || isRoleLoading}>
      <AdminPageLayout title="Edit User">
        <EditUserForm
          isEdit
          value={data}
          roleList={roleList}
          onSubmit={onUpdateUser}
          isUpdating={result.isLoading}
          isError={result.isError}
        />
      </AdminPageLayout>
    </Layout>
  );
}

EditUser.propTypes = {
  location: PropTypes.shape({ search: PropTypes.string }),
};
