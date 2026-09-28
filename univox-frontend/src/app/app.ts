import { Component, signal, ViewChild, ElementRef, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App implements OnInit {

  protected readonly title = signal('univox-frontend');
    
  abreMenu = false;

  fotosGaleria = [
  '/imagens/IMG_6237.JPEG',
  '/imagens/IMG_6226.JPEG',
  '/imagens/IMG_6227.JPEG',
  '/imagens/IMG_6236.JPEG',
  '/imagens/IMG_6249.JPEG',
  '/imagens/IMG_6300.JPG',
];

fotoAtual = 0;

  eventos: any[] = [];
  musicas: any[] = [];
  kitsVoz: any[] = [];
  partituras: any[] = [];

  musicaSelecionada: number = 1;
  vozSelecionada: number | null = null;
  tipoArquivo = 'Kit de Voz';
  mostrarVozes = true;

  eventoSelecionado: any = null;

  @ViewChild('galeriaFotos') galeriaFotos!: ElementRef;
  @ViewChild('galeriaMiniaturas') galeriaMiniaturas!: ElementRef;

  constructor(private http: HttpClient) {}

ngOnInit() {

  console.log('ngOnInit foi executado');

  this.http.get<any[]>('http://localhost:8080/agenda/todos')
    .subscribe({

      next: (dados) => {
        this.eventos = dados;
        console.log('Eventos carregados:', this.eventos);
      },

      error: (erro) => {
        console.error('Erro ao carregar eventos:', erro);
      }

    });

    this.http.get<any[]>('http://localhost:8080/musicas/todos')
  .subscribe({

    next: (dados) => {
      this.musicas = dados;
      console.log('Músicas carregadas:', this.musicas);
    },

    error: (erro) => {
      console.error('Erro ao carregar músicas:', erro);
    }

  });

  this.http.get<any[]>('http://localhost:8080/kitvoz/todos')
  .subscribe({

    next: (dados) => {
      this.kitsVoz = dados;
      console.log('Kits carregados:', this.kitsVoz);
    },

    error: (erro) => {
      console.error('Erro ao carregar kits:', erro);
    }

  });

  this.http.get<any[]>('http://localhost:8080/partituras/todos')
  .subscribe({
    next: (dados) => {
      this.partituras = dados;
      console.log('Partituras carregadas:', this.partituras);
    },
    error: (erro) => {
      console.error('Erro ao carregar partituras:', erro);
    }
  });

}

formatarData(data: string): string {

  const partes = data.split('-');

  const dia = Number(partes[2]);
  const mes = Number(partes[1]);

  const meses = [
    'JAN', 'FEV', 'MAR', 'ABR',
    'MAI', 'JUN', 'JUL', 'AGO',
    'SET', 'OUT', 'NOV', 'DEZ'
  ];

  return `${dia} ${meses[mes - 1]}`;
}

abrirDetalhes(evento: any) {
  this.eventoSelecionado = evento;
}

fecharDetalhes() {
  this.eventoSelecionado = null;
}
 
abrirMenu() {
  this.abreMenu = !this.abreMenu;
}

fecharMenu() {
  this.abreMenu = false;
}

galeriaAnterior() {
  this.galeriaFotos.nativeElement.scrollBy({
    left: -330,
    behavior: 'smooth'
  });
}

galeriaProxima() {
  this.galeriaFotos.nativeElement.scrollBy({
    left: 330,
    behavior: 'smooth'
  });
}

fotoAnterior() {
  if (this.fotoAtual > 0) {
    this.fotoAtual--;
  } else {
    this.fotoAtual = this.fotosGaleria.length - 1;
  }
}

proximaFoto() {
  if (this.fotoAtual < this.fotosGaleria.length - 1) {
    this.fotoAtual++;
  } else {
    this.fotoAtual = 0;
  }
}

miniaturasAnterior() {
  this.galeriaMiniaturas.nativeElement.scrollBy({
    top: -120,
    behavior: 'smooth'
  });
}

miniaturasProxima() {
  this.galeriaMiniaturas.nativeElement.scrollBy({
    top: 120,
    behavior: 'smooth'
  });
}

rolarMiniatura() {
  const miniaturas = this.galeriaMiniaturas.nativeElement.children;
  const miniaturaAtual = miniaturas[this.fotoAtual];

  if (miniaturaAtual) {
    miniaturaAtual.scrollIntoView({
      behavior: 'smooth',
      block: 'nearest'
    });
  }
}

alterarTipoArquivo(event: Event) {
  const select = event.target as HTMLSelectElement;

  this.tipoArquivo = select.value;
  this.mostrarVozes = this.tipoArquivo === 'Kit de Voz';
}

filtrarVozes(): any[] {

  return this.kitsVoz.filter(
    kit => kit.musica_id === Number(this.musicaSelecionada)
  );

}

baixar() {
  let arquivo = '';

  if (this.tipoArquivo === 'Kit de Voz') {

    const kit = this.kitsVoz.find(
      k => k.id === Number(this.vozSelecionada)
    );

    if (kit) {
      arquivo = kit.arquivo;
    }

  } else if (this.tipoArquivo === 'Partitura') {

    const partitura = this.partituras.find(
      p => p.musica_id === Number(this.musicaSelecionada)
    );

    if (partitura) {
      arquivo = partitura.arquivo;
    }
  }

  if (!arquivo) {
    alert('Arquivo não encontrado.');
    return;
  }

  const url = `https://ohadkunbmubxeuidzhrd.supabase.co/storage/v1/object/public/Midias/${arquivo}?download`;

  window.location.href = url;
}


}
