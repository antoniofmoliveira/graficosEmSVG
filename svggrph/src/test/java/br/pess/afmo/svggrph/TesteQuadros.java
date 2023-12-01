package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class TesteQuadros {
/**
     * 
     */
    public TesteQuadros() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGQuadros graf = new SVGQuadros();
        graf.setSeries(new double[][] {
                // { 100, 100 }, { 200, 200 }, { 300, 300 }, { 400, 400 }, {
            // 500, 500 }, { 600, 600 }, }
                {457, 339 },
                { 445, 422 },
                { 438, 420 },
                { 455, 484 },
                { 482, 514 },
                { 505, 533 },
                { 488, 549 },
                { 425, 380 },
                { 445, 521 },
                { 392, 552 },
                { 421, 292 },
                { 504, 358 },
                { 505, 361 },
                { 358, 498 },
                { 488, 486 },
                { 324, 524 },
                { 377, 393 },
                { 552, 572 },
                { 501, 424 },
                { 468, 460 },
                { 425, 373 },
                { 522, 514 },
                { 476, 299 },
                { 454, 440 },
                { 438, 410 },
                { 575, 435 },
                { 342, 534 },
                { 450, 378 },
                { 395, 503 },
                { 452, 498 },
                { 344, 475 },
                { 466, 508 },
                { 330, 401 },
                { 449, 353 },
                { 400, 329 },
                { 505, 547 },
                { 354, 473 },
                { 395, 402 },
                { 489, 336 },
                { 467, 402 },
                { 424, 478 },
                { 364, 439 },
                { 438, 326 },
                { 483, 533 },
                { 467, 411 },
                { 422, 515 },
                { 464, 229 },
                { 395, 522 },
                { 410, 394 },
                { 432, 411 },
                { 448, 485 },
                { 488, 405 },
                { 484, 543 },
                { 383, 538 },
                { 520, 528 },
                { 340, 527 },
                { 448, 522 },
                { 433, 257 },
                { 448, 458 },
                { 458, 468 },
                { 340, 504 },
                { 400, 584 },
                { 374, 499 },
                { 395, 484 },
                { 462, 528 },
                { 528, 494 },
                { 419, 496 },
                { 383, 414 },
                { 438, 524 },
                { 483, 501 },
                { 466, 503 },
                { 500, 571 },
                { 298, 368 },
                { 467, 305 },
                { 324, 363 },
                { 424, 491 },
                { 325, 468 },
                { 385, 224 },
                { 439, 565 },
                { 475, 546 },
                { 360, 480 },
                { 490, 548 },
                { 452, 489 },
                { 436, 538 },
                { 489, 494 },
                { 392, 535 },
                { 384, 489 },
                { 483, 351 },
                { 510, 534 },
                { 355, 470 },
                { 392, 465 },
                { 445, 557 },
                { 561, 494 },
                { 426, 546 },
                { 550, 556 },
                { 428, 507 },
                { 457, 476 },
                { 362, 481 },
                { 439, 517 },
                { 567, 526 },
                { 442, 486 },
                { 396, 203 },
                { 444, 521 },
                { 428, 523 },
                { 372, 476 },
                { 540, 495 },
                { 398, 474 },
                { 319, 410 },
                { 422, 519 },
                { 466, 541 },
                { 405, 540 },
                { 557, 392 },
                { 494, 459 },
                { 422, 293 },
                { 385, 342 },
                { 393, 548 },
                { 333, 497 },
                { 483, 558 },
                { 522, 497 },
                { 364, 512 },
                { 488, 389 },
                { 424, 570 },
                { 352, 542 },
                { 522, 532 },
                { 437, 512 },
                { 459, 542 },
                { 368, 540 },
                { 442, 343 },
                { 522, 458 },
                { 433, 364 },
                { 482, 267 },
                { 476, 510 },
                { 391, 503 },
                { 483, 504 },
                { 401, 505 },
                { 580, 516 },
                { 399, 496 },
                { 474, 522 },
                { 349, 563 },
                { 472, 585 },
                { 375, 374 },
                { 333, 527 },
                { 497, 578 },
                { 464, 521 },
                { 363, 519 },
                { 462, 567 },
                { 412, 311 },
                { 424, 531 },
                { 352, 497 },
                { 406, 399 },
                { 568, 433 },
                { 394, 500 },
                { 483, 474 },
                { 418, 347 },
                { 486, 587 },
                { 428, 325 },
                { 522, 202 },
                { 448, 422 },
                { 522, 479 },
                { 493, 372 },
                { 564, 362 },
                { 518, 460 },
                { 402, 397 },
                { 374, 537 },
                { 541, 546 },
                { 574, 573 },
                { 469, 551 },
                { 399, 486 },
                { 445, 489 },
                { 285, 436 },
                { 600, 474 },
                { 404, 586 },
                { 490, 573 },
                { 296, 510 },
                { 519, 591 },
                { 328, 521 },
                { 552, 514 },
                { 585, 518 },
                { 286, 515 },
                { 413, 522 },
                { 286, 475 },
                { 524, 543 },
                { 377, 497 },
                { 453, 579 },
                { 357, 514 },
                { 348, 349 },
                { 538, 519 },
                { 419, 297 },
                { 350, 551 },
                { 536, 430 },
                { 400, 550 },
                { 425, 593 },
                { 493, 355 },
                { 455, 549 },
                { 457, 538 },
                { 395, 557 },
                { 524, 221 },
                { 459, 536 },
                { 332, 542 },
                { 481, 511 },
                { 572, 490 },
                { 417, 572 },
                { 337, 570 },
                { 416, 548 },
                { 530, 562 },
                { 462, 470 },
                { 459, 541 },
                { 320, 452 },
                { 399, 384 },
                { 382, 527 },
                { 459, 540 },
                { 476, 565 },
                { 311, 547 },
                { 522, 566 },
                { 400, 565 },
                { 400, 492 },
                { 466, 412 },
                { 360, 510 },
                { 435, 508 },
                { 490, 417 },
                { 379, 529 },
                { 348, 506 },
                { 324, 536 },
                { 486, 387 },
                { 360, 503 },
                { 508, 552 },
                { 454, 578 },
                { 365, 533 },
                { 400, 531 },
                { 424, 496 },
                { 442, 563 },
                { 380, 507 },
                { 450, 549 },
                { 359, 562 },
                { 367, 508 },
                { 522, 534 },
                { 424, 456 },
                { 475, 574 },
                { 452, 561 },
                { 409, 413 },
                { 500, 505 },
                { 371, 479 },
                { 466, 581 },
                { 354, 449 },
                { 511, 574 },
                { 459, 514 },
                { 255, 506 },
                { 375, 192 },
                { 402, 544 },
                { 368, 482 },
                { 428, 539 },
                { 379, 562 },
                { 372, 397 },
                { 325, 535 },
                { 470, 371 },
                { 447, 542 },
                { 462, 542 },
                { 319, 519 },
                { 357, 501 },
                { 364, 507 },
                { 545, 492 },
                { 372, 498 },
                { 347, 548 },
                { 522, 489 },
                { 446, 524 },
                { 522, 540 },
                { 371, 571 },
                { 456, 472 },
                { 409, 554 },
                { 503, 551 },
                { 471, 436 },
                { 459, 511 },
                { 394, 505 },
                { 480, 503 },
                { 453, 395 },
                { 379, 551 },
                { 384, 533 },
                { 419, 475 },
                { 397, 442 },
                { 417, 535 },
                { 485, 579 },
                { 488, 421 },
                { 579, 515 },
                { 419, 426 },
                { 571, 591 },
                { 489, 349 },
                { 373, 555 },
                { 395, 288 },
                { 333, 550 },
                { 569, 564 },
                { 379, 522 },
                { 324, 177 },
                { 371, 537 },
                { 416, 530 },
                { 412, 551 },
                { 493, 520 },
                { 466, 562 },
                { 313, 511 },
                { 451, 568 },
                { 335, 506 },
                { 515, 511 },
                { 507, 452 },
                { 427, 311 },
                { 489, 556 },
                { 452, 493 },
                { 450, 478 },
                { 340, 355 },
                { 385, 555 },
                { 473, 486 },
                { 530, 592 },
                { 416, 532 },
                { 485, 513 },
                { 573, 556 },
                { 316, 351 },
                { 568, 422 },
                { 490, 537 },
                { 522, 391 },
                { 493, 343 },
                { 400, 535 },
                { 367, 411 },
                { 474, 523 },
                { 431, 542 },
                { 478, 522 },
                { 522, 540 },
                { 434, 519 },
                { 522, 511 },
                { 496, 383 },
                { 439, 535 },
                { 390, 487 },
                { 400, 479 },
                { 356, 508 },
                { 522, 420 },
                { 375, 412 },
                { 284, 469 },
                { 465, 202 },
                { 450, 351 },
                { 453, 380 },
                { 339, 221 },
                { 414, 356 },
                { 391, 567 },
                { 467, 531 },
                { 505, 345 },
                { 398, 370 },
                { 509, 459 },
                { 482, 293 },
                { 521, 551 },
                { 470, 490 },
                { 500, 252 },
                { 464, 516 },
                { 375, 289 },
                { 522, 552 },
                { 474, 507 },
                { 499, 544 },
                { 494, 513 },
                { 392, 575 },
                { 343, 560 },
                { 430, 502 },
                { 398, 557 },
                { 318, 531 },
                { 471, 582 },
                { 483, 579 },
                { 440, 483 },
                { 500, 554 },
                { 502, 530 },
                { 446, 497 },
                { 430, 545 },
                { 467, 546 },
                { 378, 531 },
                { 374, 537 },
                { 507, 174 },
                { 490, 257 },
                { 522, 264 },
                { 438, 574 },
                { 372, 570 },
                { 360, 511 },
                { 296, 238 },
                { 400, 499 },
                { 396, 192 },
                { 400, 529 },
                { 316, 509 },
                { 517, 553 },
                { 437, 537 },
                { 476, 575 },
                { 429, 566 },
                { 432, 477 },
                { 504, 594 },
                { 515, 498 },
                { 500, 251 },
                { 428, 585 },
                { 430, 573 },
                { 522, 594 },
                { 422, 511 },
                { 500, 508 },
                { 560, 484 },
                { 373, 504 },
                { 600, 588 },
                { 494, 584 },
                { 389, 508 },
                { 376, 545 },
                { 518, 537 },
                { 482, 527 },
                { 418, 438 },
                { 600, 551 },
                { 490, 572 },
                { 330, 490 },
                { 504, 418 },
                { 600, 557 },
                { 442, 336 },
                { 512, 544 },
                { 501, 561 },
                { 458, 283 },
                { 400, 537 },
                { 523, 599 },
                { 463, 521 },
                { 391, 540 },
                { 484, 600 },
                { 408, 533 },
                { 361, 216 },
                { 563, 554 },
                { 527, 552 },
                { 478, 584 },
                { 415, 550 },
                { 413, 548 },
                { 571, 598 },
                { 506, 580 },
                { 490, 583 },
                { 472, 545 },
                { 400, 100 },
                { 570, 585 },
                { 473, 598 },
                { 548, 600 },
                { 304, 600 },
                { 357, 534 },
                { 241, 545 },
                { 400, 100 }
            });
        graf.setTitulo("SUPERINTENDÊNCIA MG");
        graf.setYTituloLeft("Processos Internos");
        graf.setXTituloBottom("Resultado Econômico");
        graf.setBase("bas/00-bas/00");
        graf.setSize(1000);

        try {
            // graf.toSVG("quadro.svg");
            // graf.toXML("quadro.svg");
            // graf.rasterizeMe("quadro.jpg");
            graf.rasterizeMe("quadro.png");
            // graf.rasterizeMe("quadro.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}