package dev.akarshmi.quilink.redirect.serviceImpl;

import dev.akarshmi.quilink.common.mapper.UrlMappingMapperInterface;
import dev.akarshmi.quilink.exception.validation.ResourceGoneException;
import dev.akarshmi.quilink.exception.validation.ResourceNotFoundException;
import dev.akarshmi.quilink.redirect.service.RedirectService;
import dev.akarshmi.quilink.redirect.dto.RedirectResponse;
import dev.akarshmi.quilink.shortener.document.UrlMapping;
import dev.akarshmi.quilink.shortener.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class RedirectServiceImpl implements RedirectService {

    private final UrlMappingRepository urlMappingRepository;
    private final UrlMappingMapperInterface mapper;


    @Override
    public RedirectResponse getRedirect(String shortCode) {
        UrlMapping mapping= urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException(shortCode));

        if(!mapping.isActive()){
            throw new ResourceNotFoundException(shortCode);
        }

        if (mapping.getExpiresAt()!=null && mapping.getExpiresAt().isBefore(Instant.now())){
            throw new ResourceGoneException(shortCode);
        }

        mapping.setClickCount(mapping.getClickCount()+1);
        urlMappingRepository.save(mapping);

        return mapper.toRedirectResponse(mapping);
    }


}
