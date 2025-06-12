# Aplikasi Android Deteksi Penyakit Daun Kelapa Sawit

Aplikasi Android ini dikembangkan untuk membantu dalam deteksi penyakit pada daun kelapa sawit secara instan. Aplikasi ini dirancang untuk memberikan kemudahan bagi pengguna di lapangan untuk mengidentifikasi potensi penyakit dan melacak riwayat deteksi.

## Fitur Utama

Aplikasi ini telah dilengkapi dengan fitur-fitur penting untuk mendukung deteksi dan pelacakan:

1.  **Deteksi Penyakit via Kamera**: Pengguna dapat langsung mengambil gambar daun kelapa sawit menggunakan kamera perangkat mereka. Gambar ini kemudian diproses untuk mendeteksi keberadaan dan jenis penyakit pada daun.
2.  **Penyimpanan Riwayat Deteksi (History)**: Setiap hasil deteksi akan disimpan secara lokal di dalam aplikasi. Fitur ini memungkinkan pengguna untuk meninjau kembali riwayat deteksi sebelumnya, yang dapat sangat membantu dalam memantau perkembangan penyakit atau untuk keperluan pelaporan.

## Teknologi dan Library yang Digunakan

Aplikasi Android ini dibangun menggunakan teknologi modern dan *library* Jetpack yang direkomendasikan oleh Google untuk pengembangan Android, memastikan kinerja, skalabilitas, dan kemudahan pengembangan.

* **Kotlin**: Bahasa pemrograman utama yang digunakan untuk pengembangan aplikasi. Kotlin adalah bahasa modern, *concise*, dan aman yang sepenuhnya kompatibel dengan Java, dan menjadi bahasa pilihan untuk pengembangan Android.
* **Android Studio**: Lingkungan pengembangan terintegrasi (IDE) resmi untuk pengembangan Android. Android Studio menyediakan semua alat yang diperlukan untuk membangun aplikasi Android, termasuk editor kode, *debugger*, dan *emulator*.
* **Jetpack Compose**: *Toolkit* UI modern deklaratif dari Android untuk membangun antarmuka pengguna secara *native*. Jetpack Compose mempercepat pengembangan UI dengan kode yang lebih sedikit, *tool* yang kuat, dan API Kotlin yang intuitif.
* **CameraX**: *Library* pendukung Jetpack yang menyederhanakan pengembangan aplikasi kamera. CameraX menyediakan API yang konsisten dan mudah digunakan di seluruh perangkat Android, sehingga memudahkan implementasi fitur kamera untuk deteksi gambar.
* **Retrofit**: *Library* *HTTP client* yang aman dan kuat untuk Android dan Java. Retrofit digunakan untuk memfasilitasi komunikasi antara aplikasi Android dengan *backend* FastAPI, mengirimkan gambar untuk deteksi dan menerima hasil deteksi.
* **Room**: *Library* persistensi dari Jetpack yang menyediakan lapisan abstraksi di atas SQLite. Room digunakan untuk menyimpan data lokal, seperti riwayat deteksi, dalam database aplikasi dengan cara yang lebih mudah dan aman dibandingkan langsung menggunakan SQLite API. Room membantu mengelola *database* aplikasi, kueri data, dan menjaga integritas data dengan lebih efisien.

## Alur Kerja Umum

1.  Pengguna membuka aplikasi dan mengakses fitur kamera.
2.  Pengguna mengambil foto daun kelapa sawit.
3.  Aplikasi menggunakan Retrofit untuk mengirimkan foto ke *endpoint* FastAPI yang melayani model deteksi penyakit.
4.  Setelah menerima hasil deteksi dari *backend*, aplikasi akan menampilkan hasilnya kepada pengguna.
5.  Hasil deteksi beserta detail relevan (misalnya, tanggal, jenis penyakit) disimpan secara lokal menggunakan Room untuk riwayat.
6.  Pengguna dapat menelusuri riwayat deteksi mereka melalui fitur *history* aplikasi.

## Persyaratan Sistem

* Android Studio Meerkat (atau versi lebih baru)
* SDK Android (API Level 27 atau lebih tinggi direkomendasikan)
* Koneksi internet (diperlukan untuk deteksi yang mengandalkan *backend* FastAPI)