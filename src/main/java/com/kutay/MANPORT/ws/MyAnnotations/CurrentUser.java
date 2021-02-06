package com.kutay.MANPORT.ws.MyAnnotations;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER}) //Nerelerde kullanicagimizi belirtiyoruz.
@Retention(RUNTIME) // RUNTIME yani calisma aninda devreye girecegini soyluyoruz.
@AuthenticationPrincipal // Bizi "User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();" boyle bir sey yapmaktan kurtariyor.
public @interface CurrentUser {
}
