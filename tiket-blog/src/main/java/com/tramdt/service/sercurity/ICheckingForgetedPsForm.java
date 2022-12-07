package com.tramdt.service.sercurity;

import com.tramdt.model.PasswordForgettedForm;

public interface ICheckingForgetedPsForm {
    String checkPs(PasswordForgettedForm form);
    String checkPsWithConfirm(PasswordForgettedForm form);
}
