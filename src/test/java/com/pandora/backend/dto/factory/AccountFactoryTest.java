package com.pandora.backend.dto.factory;

import com.pandora.backend.domain.PermissionType;
import com.pandora.backend.fixture.AccountDtoFixture;
import com.pandora.backend.fixture.AccountFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountFactoryTest {
    @InjectMocks
    private AccountFactory subject;

    @Test
    @DisplayName("Single dto creation test.")
    public void shouldCreateAccountDtoWhenAccountIsActive() {
        var account = AccountFixture.get()
                .random()
                .deleted(Boolean.FALSE)
                .build();

        //when
        var response = subject.from(account);

        //then
        assertThat(response.getId(), equalTo(account.getId()));
        assertThat(response.getUsername(), equalTo(account.getUsername()));
        assertThat(response.getEmail(), equalTo(account.getEmail()));
        assertThat(response.getName(), equalTo(account.getName()));
        assertThat(response.getLastName(), equalTo(account.getLastName()));
        assertThat(response.getPermissionType(), equalTo(account.getPermissionType()));
        assertThat(response.isHasToSetPassword(), equalTo(account.isHasToSetPassword()));
        assertFalse(response.isDeleted());
    }

    @Test
    @DisplayName("Single dto creation test.")
    public void shouldUpdateCorrectlyAccountWithDtoData() {
        //given
        var account = AccountFixture.get()
                .random()
                .deleted(Boolean.FALSE)
                .build();

        var dto = AccountDtoFixture.get()
                .random()
                .build();

        //when
        var response = subject.update(account, dto);

        //then
        assertThat(response.getId(), equalTo(account.getId()));
        assertThat(response.getUsername(), equalTo(dto.getUsername()));
        assertThat(response.getEmail(), equalTo(dto.getEmail()));
        assertThat(response.getName(), equalTo(dto.getName()));
        assertThat(response.getLastName(), equalTo(dto.getLastName()));
        assertThat(response.getPermissionType(), equalTo(dto.getPermissionType()));
        assertThat(response.isHasToSetPassword(), equalTo(dto.isHasToSetPassword()));
        assertThat(response.isDeleted(), equalTo(dto.isDeleted()));
        assertThat(response.getPassword(), equalTo(account.getPassword()));
    }
}