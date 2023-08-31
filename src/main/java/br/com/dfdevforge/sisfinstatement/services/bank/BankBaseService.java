package br.com.dfdevforge.sisfinstatement.services.bank;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.BaseService;
import br.com.dfdevforge.sisfinstatement.entities.BankEntity;
import br.com.dfdevforge.sisfinstatement.entities.UserEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.UserUnauthorizedException;
import br.com.dfdevforge.sisfinstatement.feignclients.UserFeignClient;
import feign.FeignException;

public abstract class BankBaseService extends BaseService {
	protected BankEntity bankParam;

	@Autowired private UserFeignClient userFeignClient;

	public void setParams(BankEntity bankEntity, String token) {
		this.bankParam = bankEntity;
		this.token = token;
	}

	@Override
	public void validateUserAccess() throws BaseException {
		UserEntity userValidatedByToken = null;

		try {
			userValidatedByToken = this.userFeignClient.validateToken(this.token);

			if (this.bankParam.getUserIdentity() != userValidatedByToken.getIdentity())
				throw new UserUnauthorizedException();
		}
		catch (FeignException e) {
			throw new UserUnauthorizedException();
		}
	}
}