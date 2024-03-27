package br.com.herlandio7.ekantestspringbootapi.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.herlandio7.ekantestspringbootapi.models.UserAuth;
import br.com.herlandio7.ekantestspringbootapi.repositories.BeneficiaryRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private BeneficiaryRepository beneficiaryRepository;

    public UserDetailsServiceImpl(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return beneficiaryRepository.findByUsername(username)
                .map(UserAuth::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
