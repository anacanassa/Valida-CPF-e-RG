package com.validadorcpf.validadorcpf.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/valida-cpf")
    public String validaCpf(@RequestParam String cpf, Model model){

       boolean resultado = validaCPF(cpf);
        String mensagem = "";

        if (resultado) {
            mensagem = "CPF válido!";
        } else {
            mensagem = "CPF inválido!";
        }

        model.addAttribute("mensagem", mensagem);
        return "index";
    }

    @PostMapping("/valida-rg")
    public String validaRg(@RequestParam String rg, Model model){

        boolean resultado = validaRG(rg);
        String mensagem = "";

        if (resultado) {
            mensagem = "RG válido!";
        } else {
            mensagem = "RG inválido!";
        }

        model.addAttribute("mensagem", mensagem);
        return "index";
    }

    
	public static boolean IsNumeric(int n) {
		if(n == '0' || n == '1' || n == '2' || n == '3' || n == '4' || n == '5' || n == '6' || 
			n == '7' || n == '8' || n == '9' ) {
			return true;
		}
		else {
			return false;
		}
	}

	enum Estado { Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, ERRO }

    public static boolean validaCPF(String cpf) {

        Estado estadoCorrente = Estado.Q0;
		boolean pontuacao = false;
		boolean pontuacao2 = false;
		boolean pontuacao3 = false;
		char c;

        for (int i = 0; i < cpf.length(); i++) {
			c = cpf.charAt(i);

            switch (estadoCorrente) {
                case Q0:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q1 : Estado.ERRO;
					break;
                case Q1:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q2 : Estado.ERRO;
					break;
                case Q2:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q3 : Estado.ERRO;
					break;
				// Validação do ponto
                case Q3:
                    if(c == '.') {
						pontuacao = true;
						estadoCorrente = Estado.Q3;
					}
					else {
						if(IsNumeric(c)){
							estadoCorrente = Estado.Q4;
						}
						else {
							estadoCorrente = Estado.ERRO;
						}
					}
					break;
                case Q4:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q5 : Estado.ERRO;
					break;
                case Q5:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q6 : Estado.ERRO;
					break;
				// Validação do ponto
                case Q6:
				if(c == '.') {
					pontuacao2 = true;
					 if (pontuacao == true && pontuacao2 == true){
						estadoCorrente = Estado.Q6;
					 }
					 else {
						estadoCorrente = Estado.ERRO;
					 }
				}
				else {
					if ((pontuacao == true && pontuacao2 == true) || (pontuacao == false && pontuacao2 == false)){
						estadoCorrente = (IsNumeric(c)) ? Estado.Q7 : Estado.ERRO;
					 }
					 else {
						estadoCorrente = Estado.ERRO;
					 }
				}
				break;
                case Q7:
					estadoCorrente = (IsNumeric(c)) ? Estado.Q8 : Estado.ERRO;
					break;
				case Q8:
					estadoCorrente = (IsNumeric(c)) ? Estado.Q9 : Estado.ERRO;
					break;
				// Validação do '-'
				case Q9:
					if(c == '-') {
						pontuacao3 = true;
						if (pontuacao == true && pontuacao2 == true && pontuacao3 == true){
							estadoCorrente = Estado.Q9;
						}
						else {
							estadoCorrente = Estado.ERRO;
						}
					}
					else {
						if ((pontuacao == true && pontuacao2 == true && pontuacao3 == true) || (pontuacao == false && pontuacao2 == false && pontuacao3 == false)){
							estadoCorrente = (IsNumeric(c)) ? Estado.Q10 : Estado.ERRO;
						}
						else {
							estadoCorrente = Estado.ERRO;
						}
					}
					break;
				case Q10:
					estadoCorrente = (IsNumeric(c)) ? Estado.Q11 : Estado.ERRO;
					break;
                default:
                    estadoCorrente = Estado.ERRO;
                    break;
            }

            if (estadoCorrente == Estado.ERRO) break;
        }

        if(estadoCorrente == Estado.Q11) {
			return true;
		}
		else {
			return false;
		}
    }
    public static boolean validaRG(String rg) {

        Estado estadoCorrente = Estado.Q0;
		boolean pontuacao = false;
		boolean pontuacao2 = false;
		boolean pontuacao3 = false;
		char c;

        for (int i = 0; i < rg.length(); i++) {
			c = rg.charAt(i);

            switch (estadoCorrente) {
                case Q0:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q1 : Estado.ERRO;
                    break;
                case Q1:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q2 : Estado.ERRO;
                    break;
				// Validação do ponto
                case Q2:
                    if(c == '.') {
						pontuacao = true;
						estadoCorrente = Estado.Q2;
					}
					else {
						if(IsNumeric(c)){
							estadoCorrente = Estado.Q3;
						}
						else {
							estadoCorrente = Estado.ERRO;
						}
					}
                    break;
                case Q3:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q4 : Estado.ERRO;
                    break;
                case Q4:
                    estadoCorrente = (IsNumeric(c)) ? Estado.Q5 : Estado.ERRO;
                    break;
				// Validação do ponto
                case Q5:
					if(c == '.') {
						pontuacao2 = true;
						 if (pontuacao == true && pontuacao2 == true){
							estadoCorrente = Estado.Q5;
						 }
						 else {
							estadoCorrente = Estado.ERRO;
						 }
					}
					else {
						if ((pontuacao == true && pontuacao2 == true) || (pontuacao == false && pontuacao2 == false)){
							estadoCorrente = (IsNumeric(c)) ? Estado.Q6 : Estado.ERRO;
						 }
						 else {
							estadoCorrente = Estado.ERRO;
						 }
					}
                    break;
                case Q6:
					estadoCorrente = (IsNumeric(c)) ? Estado.Q7 : Estado.ERRO;
                    break;
				case Q7:
					estadoCorrente = (IsNumeric(c)) ? Estado.Q8 : Estado.ERRO;
                    break;
				// Validação do '-'
				case Q8:
					if(c == '-') {
						pontuacao3 = true;
						if (pontuacao == true && pontuacao2 == true && pontuacao3 == true){
							estadoCorrente = Estado.Q8;
						}
						else {
							estadoCorrente = Estado.ERRO;
						}
					}
					else {
						if ((pontuacao == true && pontuacao2 == true && pontuacao3 == true) || (pontuacao == false && pontuacao2 == false && pontuacao3 == false)){
							estadoCorrente = (IsNumeric(c)) ? Estado.Q9 : Estado.ERRO;
						}
						else {
							estadoCorrente = Estado.ERRO;
						}
					}
                    break;
                default:
                    estadoCorrente = Estado.ERRO;
                    break;
            }

            if (estadoCorrente == Estado.ERRO) break;
        }

        if(estadoCorrente == Estado.Q9) {
			return true;
		}
		else {
			return false;
		}
	}
}
