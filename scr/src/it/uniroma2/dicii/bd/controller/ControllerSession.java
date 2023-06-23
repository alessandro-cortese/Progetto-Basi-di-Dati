package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Credentials;

public interface ControllerSession {
    void start(Credentials credentials);
}
