import { Component, signal, ViewChild, ElementRef, OnInit, ChangeDetectorRef } from '@angular/core';
import { NgFor } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule, NgFor],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App implements OnInit {

  protected readonly title = signal('univox-frontend');
    
  abreMenu = false;

  fotosGaleria: any[] = [];

fotoAtual = 0;

  eventos: any[] = [];
  musicas: any[] = [];
  kitsVoz: any[] = [];
  partituras: any[] = [];
  albuns: any[] = [];
  albunsFiltrados: any[] = [];
  anoSelecionado = 2026;
  albumSelecionado: any = null;
  fotosDoAlbum: any[] = [];

  musicaSelecionada: number = 1;
  vozSelecionada: number | null = null;
  tipoArquivo = 'Kit de Voz';
  mostrarVozes = true;

  eventoSelecionado: any = null;

  @ViewChild('galeriaFotos') galeriaFotos!: ElementRef;
  @ViewChild('galeriaMiniaturas') galeriaMiniaturas!: ElementRef;

  constructor(
  private http: HttpClient,
  private cdr: ChangeDetectorRef
) {}

ngOnInit() {

  console.log('ngOnInit foi executado');

  this.http.get<any[]>('http://localhost:8080/agenda/todos')
  .subscribe({
    next: (dados) => {
      this.eventos = dados;

      this.cdr.detectChanges();

      console.log('Eventos carregados:', this.eventos);
    },
    error: (erro) => {
      console.error('Erro ao carregar agenda:', erro);
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

  this.http.get<any[]>('http://localhost:8080/fotos/todos')
  .subscribe({
    next: (dados) => {
      this.fotosGaleria = dados;

      console.log('Fotos carregadas:', this.fotosGaleria);

      this.cdr.detectChanges();
    },
    error: (erro) => {
      console.error('Erro ao carregar fotos:', erro);
    }
  });

this.http.get<any[]>('http://localhost:8080/albuns/todos')
  .subscribe({
    next: (dados) => {

      this.albuns = dados;

      this.filtrarAlbuns();

      this.selecionarAlbum(this.albunsFiltrados[0]);

      this.cdr.detectChanges();

      console.log('Álbuns carregados:', this.albuns);
      console.log('Álbuns filtrados:', this.albunsFiltrados);

    },
    error: (erro) => {
      console.error('Erro ao carregar álbuns:', erro);
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
  if (this.fotosDoAlbum.length === 0) {
    return;
  }

  if (this.fotoAtual > 0) {
    this.fotoAtual--;
  } else {
    this.fotoAtual = this.fotosDoAlbum.length - 1;
  }
}

proximaFoto() {
  if (this.fotosDoAlbum.length === 0) {
    return;
  }

  if (this.fotoAtual < this.fotosDoAlbum.length - 1) {
    this.fotoAtual++;
  } else {
    this.fotoAtual = 0;
  }
}

filtrarAlbuns() {

  this.albunsFiltrados = this.albuns.filter(album => {

    const ano = new Date(album.data).getFullYear();

    return ano === Number(this.anoSelecionado);

  });

}

selecionarAlbum(album: any) {
  this.albumSelecionado = album;

  this.fotosDoAlbum = this.fotosGaleria.filter(
    foto => foto.album_id === album.id
  );

  this.fotoAtual = 0;

  console.log('Álbum selecionado:', this.albumSelecionado);
  console.log('Fotos deste álbum:', this.fotosDoAlbum);
  console.log('URL da primeira foto:', this.fotosDoAlbum[0]?.foto_url);
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
