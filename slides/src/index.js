import Reveal from 'reveal.js';
// import Markdown from 'reveal.js/plugin/markdown/markdown.esm.js';
import RevealMarkdown from 'reveal.js/plugin/markdown/markdown.js';
import RevealHighlight from 'reveal.js/plugin/highlight/highlight.js';

let deck = new Reveal({
  // plugins: [ Markdown ]
  plugins: [ RevealMarkdown, RevealHighlight ]
})
deck.initialize({
  maxScale: 1.2
});
