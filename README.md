# BootstrapGDX
A bootstrap project for creating LibGDX games.

This is a (rather simple) bootstrap project that combines together
common stuff I tend to use whenever I make games or try out new
ideas. I don't necessarily write stuff here first before using it
in my projects; instead, I try to bring stuff here that I've used
before and noted worthy of re-use.

If you have ideas on how to improve this, feel free to create pull requests!

## Rationale

This might not be the be-all, end-all solution to all the greatest
problems of game development. The pieces that make up this project
may not be the most efficient or the most general purpose systems,
but the work for me for the most of the time.

My rationale here is to provide a nice, clean set of things I'd
end up writing or copy-pasting anyway. Whenever I get an idea for
a game mechanic, I want to start working on that right then and there.
This project will help me do just that while not blocking me from
developing it into a full-fledged game.

## Features

So far, this bootstrap project has the following things set up:

- A LibGDX project featuring Android and desktop projects

- Artemis ECS system with common systems and components in-place

- Retrolambda

- Simple resource handling

- Automatic texture atlas creation when running the desktop build

- JUnit test support in core module

## Upcoming

These are upcoming features I've planned to implement. These are
in no specific order and may or may not be implemented. Ever.

- Generic QuadTree implementation

- Box2D support

- Mockito support

## Usage with Android Studio

Clone this repo to your local machine with git:

    git clone https://github.com/manabreak/BootstrapGDX.git

Now, launch Android Studio and choose "import project", select the
BootstrapGDX folder and you're good to go!

To launch the desktop build in Android Studio, right-click on the
DesktopLauncher, choose 'create DesktopLauncher()...' and make sure
the working directory points at BootstraGDX/android/assets.

## Automagic texture atlas creation

To automatically create a texture atlas from individual images, place
all your images in the 'images' folder (create one right next to the
'android', 'core' and 'desktop' folders).

## License

This is licensed under MIT license. Feel free to grab a copy and modify
it for any purposes you like.
